package com.scrumretro.util;

import com.scrumretro.web.model.UserResponse;

public class UserThreadLocal {

	public static final ThreadLocal<UserResponse> userThreadLocal = new ThreadLocal<UserResponse>();

	public static void set(UserResponse user) {

		userThreadLocal.set(user);

	}

	public static void unset() {

		userThreadLocal.remove();

	}

	public static UserResponse get() {

		return  userThreadLocal.get();

	}

}
