package com.sandbox

import org.gradle.api.Plugin
import org.gradle.api.Project

class SamplePlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.tasks.create('sampleTask', SampleTask)
    }
}
