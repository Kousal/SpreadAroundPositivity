package ca.mini.kousel.SpreadAroundPositivity.utility;

import java.util.HashMap;
import java.util.Map;

public class JSONModelHelper {

  private JSONModelHelper() {}

  /**
   * @param rootName
   * @param collection
   * @return
   */
  private static final String STATUS_CODE = "status_code";
  private static final String STATUS_MSG = "status_msg";

  

  public static JSONModel processJSONModelForObject(String statusCode,String statusMessage, Object entity) {
    Map<String, Object> metaMap = new HashMap<>();
    metaMap.put(STATUS_CODE, statusCode);
    if (statusMessage != null && statusMessage.trim().length() > 0) {
      metaMap.put(STATUS_MSG, statusMessage);
    }
    return new JSONModel.Builder<>(entity).addMeta(metaMap).build();
  }

  
  

}
