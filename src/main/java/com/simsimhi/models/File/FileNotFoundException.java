package com.simsimhi.models.File;

import com.simsimhi.commons.Utils;
import com.simsimhi.commons.exceptions.CommonException;
import org.springframework.http.HttpStatus;

public class FileNotFoundException extends CommonException {

    public FileNotFoundException() {
        super(Utils.getMessage("NotFound.file", "error"),
                HttpStatus.NOT_FOUND);
    }

}