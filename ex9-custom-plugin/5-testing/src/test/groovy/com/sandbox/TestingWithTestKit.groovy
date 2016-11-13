package com.sandbox

import org.gradle.testkit.runner.GradleRunner
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

import static org.gradle.testkit.runner.TaskOutcome.SUCCESS

class TestingWithTestKit extends Specification {
    @Rule
    final TemporaryFolder testProjectDir = new TemporaryFolder()
    File buildFile

    def setup() {
        buildFile = testProjectDir.newFile('build.gradle')
    }

    def "sample task execution"() {
        given:
        // apply plugin: 'sample-plugin' - does not work
        buildFile << """
            plugins {
              id 'sample-plugin'
            }
        """

        when:
        def result = GradleRunner.create()
                .withProjectDir(testProjectDir.root)
                .withArguments('sampleTask')
                // since 2.13
                .withPluginClasspath()
                .build()

        then:
        result.output.contains('sample task output')
        result.task(":sampleTask").outcome == SUCCESS
    }
}
