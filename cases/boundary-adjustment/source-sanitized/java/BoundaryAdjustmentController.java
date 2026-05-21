package gov.example.admin.boundary;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Sanitized example controller.
 *
 * Production package names, API paths, work codes, authorization groups, and
 * audit-log identifiers were replaced with public example names.
 */
@Controller
@RequestMapping("/example/boundary-adjustment")
public class BoundaryAdjustmentController {

    private final BoundaryAdjustmentService boundaryAdjustmentService;

    public BoundaryAdjustmentController(BoundaryAdjustmentService boundaryAdjustmentService) {
        this.boundaryAdjustmentService = boundaryAdjustmentService;
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(Model model, @RequestParam Map<String, Object> params) {
        model.addAttribute("params", params);
        return "cases/boundary-adjustment";
    }

    @RequestMapping(value = "/files", method = RequestMethod.GET)
    public String listFiles(Model model, @RequestParam Map<String, Object> params) {
        model.addAttribute("stageAFileList", boundaryAdjustmentService.findFiles(params, "STAGE_A"));
        model.addAttribute("stageBFileList", boundaryAdjustmentService.findFiles(params, "STAGE_B"));
        return "jsonView";
    }

    @RequestMapping(value = "/file-parcels", method = RequestMethod.GET)
    public String listFileParcels(Model model, @RequestParam Map<String, Object> params) {
        model.addAttribute("list", boundaryAdjustmentService.findLinkedParcels(params));
        return "jsonView";
    }

    @RequestMapping(value = "/parcels/search", method = RequestMethod.GET)
    public String searchParcels(Model model, @RequestParam Map<String, Object> params) {
        model.addAttribute("list", boundaryAdjustmentService.searchParcels(params));
        return "jsonView";
    }

    @RequestMapping(value = "/map/boundaries", method = RequestMethod.GET)
    public String listBoundaryGeometry(Model model, @RequestParam Map<String, Object> params) {
        model.addAttribute("boundaryList", boundaryAdjustmentService.findBoundaryGeometry(params));
        return "jsonView";
    }

    @RequestMapping(value = "/adjustment-counts", method = RequestMethod.GET)
    public String listAdjustmentCounts(Model model, @RequestParam Map<String, Object> params) {
        model.addAttribute("list", boundaryAdjustmentService.findAdjustmentCounts(params));
        return "jsonView";
    }

    @RequestMapping(value = "/files", method = RequestMethod.POST)
    public String registerFile(
            Model model,
            @RequestParam Map<String, Object> params,
            @RequestParam("file") MultipartFile file) {

        if (isBlank(params.get("projectId")) || file == null || file.isEmpty()) {
            model.addAttribute("errorMessage", "Invalid request.");
            return "jsonView";
        }

        boundaryAdjustmentService.registerFileWithParcels(file, params);
        model.addAttribute("successMessage", "Saved.");
        model.addAttribute("fileList", boundaryAdjustmentService.findFiles(params, null));
        return "jsonView";
    }

    @RequestMapping(value = "/file-parcels", method = RequestMethod.PUT)
    public String updateFileParcels(Model model, @RequestParam Map<String, Object> params) {
        if (isBlank(params.get("fileMapId")) || isBlank(params.get("projectId"))) {
            model.addAttribute("errorMessage", "Invalid request.");
            return "jsonView";
        }

        boundaryAdjustmentService.replaceLinkedParcels(params);
        model.addAttribute("successMessage", "Updated.");
        return "jsonView";
    }

    @RequestMapping(value = "/files", method = RequestMethod.DELETE)
    public String deleteFile(Model model, @RequestParam Map<String, Object> params) {
        if (isBlank(params.get("fileMapId"))) {
            model.addAttribute("errorMessage", "Invalid request.");
            return "jsonView";
        }

        boundaryAdjustmentService.deleteFile(params);
        model.addAttribute("successMessage", "Deleted.");
        return "jsonView";
    }

    private boolean isBlank(Object value) {
        return value == null || String.valueOf(value).trim().isEmpty();
    }

    @SuppressWarnings("unused")
    private Map<String, Object> auditContext(String action) {
        Map<String, Object> audit = new HashMap<String, Object>();
        audit.put("action", action);
        audit.put("workCode", "REDACTED");
        return audit;
    }
}
