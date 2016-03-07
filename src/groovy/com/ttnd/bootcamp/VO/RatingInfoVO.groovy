package com.ttnd.bootcamp.VO

/**
 * Created by aditi on 24/2/16.
 */
class RatingInfoVO {
    Integer totalVotes
    Integer totalScore
    Integer averageScore

    String toString() {
        "${totalVotes} : ${totalScore} : ${averageScore}"
    }
}
