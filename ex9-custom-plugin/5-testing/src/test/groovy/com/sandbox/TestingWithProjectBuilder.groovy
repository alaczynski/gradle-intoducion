package com.sandbox

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

class TestingWithProjectBuilder extends Specification {

    def "sample plugin creates sample task"() {
        given:
        Project project = ProjectBuilder.builder().build()
        project.pluginManager.apply(SamplePlugin)

        expect:
        project.tasks.sampleTask instanceof SampleTask
    }
}
