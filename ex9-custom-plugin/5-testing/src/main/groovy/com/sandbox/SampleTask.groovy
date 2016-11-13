package com.sandbox

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class SampleTask extends DefaultTask {

    @TaskAction
    def someAction() {
        println 'sample task output'
    }
}
