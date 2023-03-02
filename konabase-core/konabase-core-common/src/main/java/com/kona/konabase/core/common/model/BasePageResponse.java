package com.kona.konabase.core.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor(staticName = "of")
public class BasePageResponse<T> {

  List<T> dataList;
  long totalCount;
  int pageNo;
  int pageSize;
}
