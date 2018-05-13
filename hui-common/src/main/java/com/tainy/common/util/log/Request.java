package com.tainy.common.util.log;

import com.tainy.common.util.disSeq.IdCreater;

public class Request {

	public static String id;
	private static ThreadLocal<String> idLocal = new ThreadLocal<String>();

	public static void initId() {
		if (idLocal.get() == null) {
			id = IdCreater.getNextId() + "";
			idLocal.set(id);
		}
	}

	public static String getId() {
		return idLocal.get();
	}

}
