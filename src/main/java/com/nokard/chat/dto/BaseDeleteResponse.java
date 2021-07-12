package com.nokard.chat.dto;

import com.nokard.chat.utils.DateUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@NoArgsConstructor
public class BaseDeleteResponse {
    protected Long timestamp = DateUtils.nowUnix();
}
