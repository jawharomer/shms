package com.joh.shms.service;

import java.util.List;

import com.joh.shms.domain.model.NotificationD;

public interface ReportService {

	List<NotificationD> findAdminNotifications();

}
