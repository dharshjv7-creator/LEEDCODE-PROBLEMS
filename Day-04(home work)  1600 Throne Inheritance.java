class ThroneInheritance {

    class Person {
        String name;
        List<Person> children = new ArrayList<>();

        Person(String name) {
            this.name = name;
        }
    }

    Map<String, Person> map;
    Set<String> dead;
    Person king;

    public ThroneInheritance(String kingName) {
        map = new HashMap<>();
        dead = new HashSet<>();

        king = new Person(kingName);
        map.put(kingName, king);
    }

    public void birth(String parentName, String childName) {
        Person parent = map.get(parentName);

        Person child = new Person(childName);
        parent.children.add(child);

        map.put(childName, child);
    }

    public void death(String name) {
        dead.add(name);
    }

    public List<String> getInheritanceOrder() {
        List<String> order = new ArrayList<>();
        dfs(king, order);
        return order;
    }

    private void dfs(Person person, List<String> order) {
        if (!dead.contains(person.name)) {
            order.add(person.name);
        }

        for (Person child : person.children) {
            dfs(child, order);
        }
    }
}
