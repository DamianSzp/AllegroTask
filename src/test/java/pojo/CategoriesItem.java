package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
@JsonIgnoreProperties(ignoreUnknown = true)
public @Data class CategoriesItem{
	private Parent parent;
	private String name;
	private Options options;
	private String id;
	private boolean leaf;
}