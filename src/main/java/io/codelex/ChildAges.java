package io.codelex;

public record ChildAges(int age1, int age2, int age3) {

    public int getSum() {
        return age1 + age2 + age3;
    }

    public boolean hasEldestSon() {
        return age3 > age1 && age3 > age2;
    }
}
