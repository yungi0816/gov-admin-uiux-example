<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!--
  Sanitized JSP/JavaScript excerpt.

  The production screen used the existing JSP, jQuery, OpenLayers, common AJAX,
  authorization, and audit-log conventions. Internal function names and API
  paths were replaced with public example names.
-->

<style>
  #boundaryMapPanel {
    position: relative;
    width: 550px;
    height: 320px;
    border: 1px solid #ccc;
  }

  #boundaryModeLabel {
    position: absolute;
    right: 12px;
    top: 10px;
    z-index: 10;
    padding: 4px 10px;
    color: #fff;
    font-size: 12px;
    font-weight: 700;
    background: #e53935;
  }

  #adjustmentLegend {
    position: absolute;
    left: 10px;
    bottom: 10px;
    z-index: 10;
    min-width: 150px;
    background: rgba(255, 255, 255, 0.95);
    border: 1px solid #cfd8dc;
  }

  .legendRow {
    display: flex;
    align-items: center;
    gap: 8px;
    padding: 3px 10px;
    font-size: 11px;
  }

  .legendColor {
    width: 28px;
    height: 14px;
    border: 1px solid #ccc;
  }
</style>

<script>
  var boundaryMap;
  var boundaryLayer;
  var selectedParcelIds = [];
  var currentMode = "create";
  var adjustmentOpacity = 0.6;

  function initBoundaryAdjustmentMap() {
    boundaryLayer = new ol.layer.Vector({
      source: new ol.source.Vector(),
      style: boundaryStyle
    });

    boundaryMap = new ol.Map({
      target: "boundaryMap",
      layers: [createBaseMapLayer(), boundaryLayer],
      view: new ol.View({
        projection: getProjectProjection(),
        center: [200000, 500000],
        zoom: 12
      }),
      controls: ol.control.defaults({
        attribution: false,
        zoom: false,
        rotate: false
      })
    });

    boundaryMap.on("click", function(event) {
      boundaryMap.forEachFeatureAtPixel(event.pixel, function(feature) {
        var parcelId = feature.get("parcelId");
        if (!parcelId) return true;

        if (currentMode === "view") {
          resetBoundaryForm();
        }

        if (selectedParcelIds.indexOf(parcelId) >= 0) {
          removeSelectedParcel(parcelId);
        } else {
          feature.set("selected", true);
          feature.changed();
          loadParcelSummary(parcelId);
        }

        return true;
      });
    });

    loadBoundaryGeometry();
  }

  function boundaryStyle(feature) {
    var adjustmentCount = feature.get("adjustmentCount") || 0;
    var isSelected = feature.get("selected") === true;

    return new ol.style.Style({
      stroke: new ol.style.Stroke({
        color: isSelected ? "rgba(255, 0, 0, 0.9)" : "rgb(111, 84, 0)",
        width: isSelected ? 2.5 : 1.5
      }),
      fill: new ol.style.Fill({
        color: isSelected ? "rgba(255, 0, 0, 0.08)" : adjustmentFillColor(adjustmentCount)
      }),
      text: new ol.style.Text({
        text: feature.get("parcelLabel") || "",
        font: "bold 11px sans-serif",
        fill: new ol.style.Fill({ color: "rgb(80, 60, 0)" }),
        stroke: new ol.style.Stroke({ color: "#fff", width: 2 })
      })
    });
  }

  function adjustmentFillColor(count) {
    if (count >= 5) return "rgba(229, 57, 53, " + adjustmentOpacity + ")";
    if (count === 4) return "rgba(251, 140, 0, " + adjustmentOpacity + ")";
    if (count === 3) return "rgba(253, 216, 53, " + adjustmentOpacity + ")";
    if (count === 2) return "rgba(67, 160, 71, " + adjustmentOpacity + ")";
    if (count === 1) return "rgba(30, 136, 229, " + adjustmentOpacity + ")";
    return "rgba(111, 84, 0, 0.03)";
  }

  function changeAdjustmentOpacity(value) {
    adjustmentOpacity = parseFloat(value);
    boundaryLayer.getSource().getFeatures().forEach(function(feature) {
      feature.changed();
    });
    $(".legendColor").css("opacity", adjustmentOpacity);
  }

  function loadBoundaryGeometry() {
    exampleAjax("/example/boundary-adjustment/map/boundaries", {
      projectId: $("#projectId").val()
    }, function(response) {
      var reader = new ol.format.WKT();
      var features = [];

      response.boundaryList.forEach(function(item) {
        var feature = reader.readFeature(item.geometryWkt, {
          dataProjection: getProjectProjection(),
          featureProjection: getProjectProjection()
        });
        feature.setProperties(item);
        features.push(feature);
      });

      boundaryLayer.getSource().clear();
      boundaryLayer.getSource().addFeatures(features);
    });
  }

  function loadParcelSummary(parcelId) {
    exampleAjax("/example/boundary-adjustment/parcels/search", {
      projectId: $("#projectId").val(),
      parcelId: parcelId
    }, function(response) {
      if (response.list.length > 0) {
        addSelectedParcel(response.list[0], false);
      }
    });
  }

  function addSelectedParcel(parcel, readOnly) {
    if (selectedParcelIds.indexOf(parcel.parcelId) >= 0) return;

    selectedParcelIds.push(parcel.parcelId);
    $("#selectedParcelRows").append(
      "<tr data-parcel-id='" + parcel.parcelId + "'>" +
      "<td>" + escapeHtml(parcel.displayAddress) + "</td>" +
      "<td>" + escapeHtml(parcel.parcelLabel) + "</td>" +
      "<td>" + escapeHtml(parcel.landCategoryName) + "</td>" +
      "<td class='alignRight'>" + escapeHtml(parcel.areaText) + "</td>" +
      "<td>" + (readOnly ? "" : "<button type='button' onclick='removeSelectedParcel(\"" + parcel.parcelId + "\")'>삭제</button>") + "</td>" +
      "</tr>"
    );
    $("#parcelIdList").val(selectedParcelIds.join(","));
  }

  function removeSelectedParcel(parcelId) {
    selectedParcelIds = selectedParcelIds.filter(function(value) {
      return value !== parcelId;
    });
    $("#selectedParcelRows tr[data-parcel-id='" + parcelId + "']").remove();
    $("#parcelIdList").val(selectedParcelIds.join(","));

    boundaryLayer.getSource().getFeatures().forEach(function(feature) {
      if (feature.get("parcelId") === parcelId) {
        feature.set("selected", false);
        feature.changed();
      }
    });
  }

  function setBoundaryMode(mode) {
    currentMode = mode;
    var labels = {
      create: ["등록 모드", "#e53935"],
      view: ["조회 모드", "#43a047"],
      edit: ["수정 모드", "#fb8c00"]
    };
    $("#boundaryModeLabel").text(labels[mode][0]).css("background", labels[mode][1]);
  }

  function resetBoundaryForm() {
    selectedParcelIds = [];
    $("#selectedParcelRows").empty();
    $("#parcelIdList").val("");
    setBoundaryMode("create");
    boundaryLayer.getSource().getFeatures().forEach(function(feature) {
      feature.set("selected", false);
      feature.changed();
    });
  }

  function escapeHtml(value) {
    return $("<div>").text(value || "").html();
  }
</script>

<form id="boundaryAdjustmentForm" method="post" enctype="multipart/form-data">
  <input type="hidden" id="projectId" name="projectId" value="${param.projectId}" />
  <input type="hidden" id="parcelIdList" name="parcelIdList" value="" />

  <div id="boundaryMapPanel">
    <div id="boundaryMap" style="width:100%; height:100%;"></div>
    <span id="boundaryModeLabel">등록 모드</span>

    <div id="adjustmentLegend">
      <strong style="display:block; padding:8px 10px; border-bottom:1px solid #eee;">경계조정 횟수</strong>
      <div class="legendRow"><span class="legendColor" style="background:#e53935; opacity:.6;"></span>5회 이상</div>
      <div class="legendRow"><span class="legendColor" style="background:#fb8c00; opacity:.6;"></span>4회</div>
      <div class="legendRow"><span class="legendColor" style="background:#fdd835; opacity:.6;"></span>3회</div>
      <div class="legendRow"><span class="legendColor" style="background:#43a047; opacity:.6;"></span>2회</div>
      <div class="legendRow"><span class="legendColor" style="background:#1e88e5; opacity:.6;"></span>1회</div>
      <input type="range" min="0" max="1" step="0.1" value="0.6" oninput="changeAdjustmentOpacity(this.value)" />
    </div>
  </div>

  <table>
    <caption>선택된 필지 목록</caption>
    <thead>
      <tr>
        <th>소재지</th>
        <th>지번</th>
        <th>지목</th>
        <th>면적</th>
        <th>관리</th>
      </tr>
    </thead>
    <tbody id="selectedParcelRows"></tbody>
  </table>

  <label for="boundaryFile">경계조정 파일</label>
  <input type="file" id="boundaryFile" name="file" accept=".sdb,.dat" />
</form>
