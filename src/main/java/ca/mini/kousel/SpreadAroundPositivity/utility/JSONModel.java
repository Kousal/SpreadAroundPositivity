package ca.mini.kousel.SpreadAroundPositivity.utility;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.util.Assert;



public final class JSONModel extends ConcurrentHashMap<String, Object> {

  private static final long serialVersionUID = 1L;

  private JSONModel() {
    // Must use the builder
  }

  public static class Builder<T> implements BuilderService<JSONModel> {
    private final ConcurrentMap<String, Object> sideLoadedItems = new ConcurrentHashMap<>();
    private final ConcurrentMap<String, Object> metaData = new ConcurrentHashMap<>();
    private final ConcurrentMap<String, Object> links = new ConcurrentHashMap<>();
    private final ConcurrentMap<String, Object> errors = new ConcurrentHashMap<>();

    public Builder() {}

    public Builder(final Object entity) {
      Assert.notNull(entity, "Object passed as null");
      sideLoad(entity);
    }

    public Builder(final Collection<T> entities) {
      Assert.notNull(entities, "Objects passed as null");
      sideLoad(entities);
    }

    @Override
    public JSONModel build() {
      if (!metaData.isEmpty()) {
        sideLoadedItems.put("meta", metaData);
      }
      if (!links.isEmpty()) {
        sideLoadedItems.put("links", links);
      }
      if (!errors.isEmpty()) {
        sideLoadedItems.put("errors", errors);
      }
      JSONModel sideLoader = new JSONModel();
      sideLoader.putAll(sideLoadedItems);
      return sideLoader;
    }

    public Builder<T> addMeta(final Map<String, Object> metaMap) {
      for (Map.Entry<String, Object> entry : metaMap.entrySet()) {
        metaData.put(entry.getKey(), entry.getValue());
      }
      return this;
    }

    public Builder<T> addLinks(final Map<String, Object> linksMap) {
      for (Map.Entry<String, Object> entry : linksMap.entrySet()) {
        links.put(entry.getKey(), entry.getValue());
      }
      return this;
    }
    
    public Builder<T> addErrors(final Map<String, Object> linksMap) {
      for (Map.Entry<String, Object> entry : linksMap.entrySet()) {
        errors.put(entry.getKey(), entry.getValue());
      }
      return this;
    }
    

    public Builder<T> addMetaDataAndLinks(final Map<String, Object> metaMap, final Map<String, Object> linksMap) {
      for (Map.Entry<String, Object> entry : metaMap.entrySet()) {
        metaData.put(entry.getKey(), entry.getValue());
      }
      for (Map.Entry<String, Object> entry : linksMap.entrySet()) {
        links.put(entry.getKey(), entry.getValue());
      }
      return this;
    }

    public Builder<T> sideLoad(final Object entity) {
      if (entity != null) {
        sideLoadedItems.put("data", entity);
      }
      return this;
    }

    public <K> Builder<T> sideLoad(final Collection<K> entities) {
      if (entities != null) {
        sideLoadedItems.put("data", entities);
      }
      return this;
    }
  }

}
