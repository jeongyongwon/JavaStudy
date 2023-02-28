package enum_;

public enum Gender {
    MALE("XY"),
    FEMALE("XX");

    private String chromosome;
    Gender(String chromosome) {
        this.chromosome = chromosome;
    }

    @Override
    public String toString() { // 자신이 가지고 있는 값을 출력하게 할 수 있다.
        return chromosome;
    }

    public void print() {
        System.out.println("염색체 정보 :   " + chromosome);
    }
}
