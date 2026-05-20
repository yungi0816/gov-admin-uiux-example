package gov.example.admin.boundary;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * Data-access boundary for the public example.
 *
 * Method names describe responsibilities. Mapper namespaces, schema owners,
 * and production table/view names are intentionally omitted.
 */
public interface BoundaryAdjustmentRepository {

    List<Map<String, Object>> selectFileList(Map<String, Object> params);

    List<Map<String, Object>> selectLinkedParcelList(Map<String, Object> params);

    List<Map<String, Object>> selectSearchParcelList(Map<String, Object> params);

    List<Map<String, Object>> selectBoundaryGeometry(Map<String, Object> params);

    List<Map<String, Object>> selectAdjustmentCountList(Map<String, Object> params);

    Object insertFileMetadata(MultipartFile file, Map<String, Object> params);

    Object insertBoundaryFileRecord(Map<String, Object> params);

    void insertFileMapping(Map<String, Object> params);

    void insertParcelLink(Map<String, Object> params);

    void deleteParcelLinks(Map<String, Object> params);

    void deleteFileRecord(Map<String, Object> params);

    void deleteFileMapping(Map<String, Object> params);

    void deleteStoredFile(Map<String, Object> params);
}
