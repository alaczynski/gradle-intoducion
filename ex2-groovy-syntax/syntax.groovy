// 1. Property accessors
public class User {
    private String name;
    public User(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}

bean = new User("name1")
assert bean.name == "name1"
bean.name = "name2"
assert bean.name == "name2"

// 2. Method calls can omit the parentheses if there is at least one parameter
def sum(int a, int b) {
    a + b
}

int result = sum 1, 2
assert result == 3

// 3. List
List<Integer> list = [1, 2, 3]
assert list.size() == 3

// 4. Map
Map<String, Integer> map = [a: 1, "b": 2]
assert map.'a' == 1
map.b = 3
assert map['b'] == 3

// 5. Named parameters
def applyMap(Map<String, Integer> map) {
    assert map.a == 1
    assert map.b == 2
}

applyMap(a: 1, b: 2)
applyMap a: 1, b: 2

// 6. Closures as the last parameter in a method
def applyClosure(Closure closure) {
    closure()
}

applyClosure() { println "b" }
applyClosure({ println "a" })
applyClosure {
    println "c"
}

// 7. Closure delegate
Closure closure = {
    apply plugin: 'java'
}

def project = new Project()
closure.delegate = project
closure.resolveStrategy = Closure.DELEGATE_FIRST
closure()
assert project.plugins == ['java']

class Project {
    def plugins = []
    def dependencies = ['compile': []]

    def apply(Map<String, String> map) {
        plugins.add(map['plugin'])
    }

    def Object methodMissing(String name, Object args) {
        def dependency = (args as Object[])[0]
        dependencies[name] += dependency
    }
}

// 8. Closure delegate with missing method
Closure dependenciesClosure = {
    compile 'junit:junit:4.11'
    compile 'org.apache.commons:commons-lang3:3.4'
}
def dependencyHandler = new DependencyHandler()
dependenciesClosure.delegate = dependencyHandler
dependenciesClosure.resolveStrategy = Closure.DELEGATE_FIRST
dependenciesClosure()
assert dependencyHandler.dependencies.compile == ['junit:junit:4.11', 'org.apache.commons:commons-lang3:3.4']

class DependencyHandler {
    def dependencies = ['compile': []]

    def Object methodMissing(String name, Object args) {
        def dependency = (args as Object[])[0]
        dependencies[name] += dependency
    }
}

// 9. configuration via extension object