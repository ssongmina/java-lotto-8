package lotto.model;

public enum Rank {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(5, false, 5_000),
    NONE(0, false, 0);

    private final int matchCount;
    private final boolean matchBonus;
    private final int prize;


    Rank(int matchCount, boolean matchBonus, int prize) {
        this.matchCount = matchCount;
        this.matchBonus = matchBonus;
        this.prize = prize;
    }

    public static Rank getRank(int matchCount, boolean matchBonus){
        if(matchCount == 6) return FIRST;
        if(matchCount == 5 && matchBonus) return SECOND;
        if(matchCount == 5) return THIRD;
        if(matchCount == 4) return FOURTH;
        if(matchCount == 3) return FIFTH;
        return NONE;
    }

    public static String getMessage(Rank rank){
        if(rank == FIRST) return "6개 일치 (2,000,000,000원) - ";
        if(rank == SECOND) return "5개 일치, 보너스 볼 일치 (30,000,000원) - ";
        if(rank == THIRD) return "5개 일치 (1,500,000원) - ";
        if(rank == FOURTH) return "4개 일치 (50,000원) - ";
        if(rank == FIFTH) return "3개 일치 (5,000원) - ";
        return "";
    }

}
