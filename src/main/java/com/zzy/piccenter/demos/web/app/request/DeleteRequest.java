package com.zzy.piccenter.demos.web.app.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author T041018
 */
@Data
public class DeleteRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    private static final long serialVersionUID = 1L;
}
