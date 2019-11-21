package com.dyz.commentservice.sal.bo;

import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

import org.apache.commons.lang3.time.DateUtils;

import com.dyz.commentservice.common.constant.ServiceConstant;
import com.dyz.commentservice.common.exception.IllegalParamException;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentQueryBo {

	private Integer targetResourceId;
	
	private Integer publisherId;
	
	private CommentType type;
	
	private Date fromTime;
	
	private Date toTime;
	
	public Date getFromTime() {
		if (Objects.isNull(fromTime)) {
			try {
				return DateUtils.parseDate(ServiceConstant.DEFAULT_FROM_DATE, ServiceConstant.DATE_FORMAT_SHORT);
			} catch (ParseException e) {
				throw new IllegalParamException(0, "Illega param fromTime");
			}
		}
		return fromTime;
	}

	public Date getToTime() {
		if (Objects.isNull(toTime)) {
			try {
				return DateUtils.parseDate(ServiceConstant.DEFAULT_TO_DATE, ServiceConstant.DATE_FORMAT_SHORT);
			} catch (ParseException e) {
				throw new IllegalParamException(0, "Illega param toTime");
			}
		}
		return toTime;
	}
}
