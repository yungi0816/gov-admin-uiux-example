package gov.example.admin.boundary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * Sanitized service example for the boundary-adjustment review case.
 *
 * The original implementation had to stay compatible with an existing
 * map-based parameter style. This example keeps that constraint visible while
 * isolating the normalization and file-parcel linking responsibilities.
 */
public class BoundaryAdjustmentService {

    private final BoundaryAdjustmentRepository repository;

    public BoundaryAdjustmentService(BoundaryAdjustmentRepository repository) {
        this.repository = repository;
    }

    public List<Map<String, Object>> findFiles(Map<String, Object> params, String stageCode) {
        params.put("stageCode", stageCode);
        return repository.selectFileList(params);
    }

    public List<Map<String, Object>> findLinkedParcels(Map<String, Object> params) {
        return repository.selectLinkedParcelList(params);
    }

    public List<Map<String, Object>> searchParcels(Map<String, Object> params) {
        return repository.selectSearchParcelList(params);
    }

    public List<Map<String, Object>> findBoundaryGeometry(Map<String, Object> params) {
        return repository.selectBoundaryGeometry(params);
    }

    public List<Map<String, Object>> findAdjustmentCounts(Map<String, Object> params) {
        return repository.selectAdjustmentCountList(params);
    }

    public void registerFileWithParcels(MultipartFile file, Map<String, Object> params) {
        validateAllowedExtension(file.getOriginalFilename());

        Object storedFileId = repository.insertFileMetadata(file, params);
        params.put("storedFileId", storedFileId);

        Object fileMapId = repository.insertBoundaryFileRecord(params);
        params.put("fileMapId", fileMapId);

        repository.insertFileMapping(params);
        insertParcelLinks(params, normalizeParcelIds(params.get("parcelIdList")));
    }

    public void replaceLinkedParcels(Map<String, Object> params) {
        repository.deleteParcelLinks(params);
        insertParcelLinks(params, normalizeParcelIds(params.get("parcelIdList")));
    }

    public void deleteFile(Map<String, Object> params) {
        repository.deleteParcelLinks(params);
        repository.deleteFileRecord(params);
        repository.deleteFileMapping(params);
        repository.deleteStoredFile(params);
    }

    private void insertParcelLinks(Map<String, Object> params, List<String> parcelIds) {
        for (String parcelId : parcelIds) {
            params.put("parcelId", parcelId);
            repository.insertParcelLink(params);
        }
    }

    private List<String> normalizeParcelIds(Object rawValue) {
        if (rawValue == null) {
            return Collections.emptyList();
        }

        List<String> parcelIds = new ArrayList<String>();

        if (rawValue instanceof String[]) {
            for (String parcelId : (String[]) rawValue) {
                addIfPresent(parcelIds, parcelId);
            }
        } else if (rawValue instanceof Iterable) {
            for (Object parcelId : (Iterable<?>) rawValue) {
                addIfPresent(parcelIds, String.valueOf(parcelId));
            }
        } else {
            String value = String.valueOf(rawValue);
            for (String parcelId : value.split(",")) {
                addIfPresent(parcelIds, parcelId);
            }
        }

        return parcelIds;
    }

    private void addIfPresent(List<String> parcelIds, String parcelId) {
        if (parcelId != null && !parcelId.trim().isEmpty()) {
            parcelIds.add(parcelId.trim());
        }
    }

    private void validateAllowedExtension(String fileName) {
        if (fileName == null) {
            throw new IllegalArgumentException("File name is required.");
        }

        String lowerName = fileName.toLowerCase();
        if (!lowerName.endsWith(".sdb") && !lowerName.endsWith(".dat")) {
            throw new IllegalArgumentException("Only SDB and DAT files are allowed.");
        }
    }
}
