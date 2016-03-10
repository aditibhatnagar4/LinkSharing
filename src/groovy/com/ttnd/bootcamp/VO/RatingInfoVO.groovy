package com.ttnd.bootcamp.VO

class RatingInfoVO {
    Integer totalVotes
    Integer totalScore
    Integer averageScore

    String toString() {
        "${totalVotes} : ${totalScore} : ${averageScore}"
    }
}
