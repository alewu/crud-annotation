package ${packageName}.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
/**
  * @author ${author}
  * @date ${date}
  * @description 通用字段
  */
@Data
public class BaseEntity implements Serializable {

    /**
     * 创建日期时间
     */
    @JsonIgnore
    private Date gmtCreate;
    /**
     * 更新日期时间
     */
    @JsonIgnore
    private Date gmtModified;
}

