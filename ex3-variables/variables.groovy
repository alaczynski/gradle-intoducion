String localScope1 = 'localScope1'
def localScope2 = 'localScope2'
scriptScope = 'scriptScope'

// access from script
println "access from script: " + localScope1
println "access from script: " + localScope2
println "access from script: " + scriptScope

// access from closure
closure = {
    println "access from closure: " + localScope1
    println "access from closure: " + localScope2
    println "access from closure: " + scriptScope
}
closure.call()

// access from method
def method() {
    try {
        println "access from method: " + localScope1
        assert false
    } catch (MissingPropertyException e) {
        println "access from method: " + e.message
    }
    try {
        localScope2
        assert false
    } catch (MissingPropertyException e) {
        println "access from method: " + e.message
    }
    println "access from method: " + scriptScope
}
method()