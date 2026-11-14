package com.practice;

import java.util.List;

/** A user of the API gateway. */
public record User(String id, String name, String role, List<String> permissions) {}
