package com.ap.creditreport.models

/**
 * Data class detailing a Credit Score Report.
 * @param accountIDVStatus
 * @param creditReportInfo
 * @param dashboardStatus
 * @param coachingSummary
 */
data class CreditScoreReport(
    val accountIDVStatus: TestResult,
    val creditReportInfo: CreditReportInfo,
    val dashboardStatus: TestResult,
    val coachingSummary: CoachingSummary
)

/**
 * Data class detailing a Coaching Summary.
 * @param activeTodo
 * @param activeChat
 * @param numberOfTodoItems
 * @param numberOfCompletedTodoItems
 * @param selected
 */
data class CoachingSummary(
    val activeTodo: Boolean,
    val activeChat: Boolean,
    val numberOfTodoItems: Int,
    val numberOfCompletedTodoItems: Int,
    val selected: Boolean
)

/**
 * Data class for detailed credit report info.
 * @param score
 * @param scoreBand
 * @param clientRef
 * @param status
 * @param maxScoreValue
 * @param minScoreValue
 * @param monthsSinceLastDefaulted
 * @param hasEverDefaulted
 * @param monthsSinceLastDelinquent
 * @param hasEverBeenDelinquent
 * @param percentageCreditUsed
 * @param percentageCreditUsedDirectionFlag
 * @param changedScore
 * @param currentShortTermDebt
 * @param currentShortTermNonPromotionalDebt
 * @param currentShortTermCreditLimit
 * @param currentShortTermCreditUtilisation
 * @param currentLongTermDebt
 * @param currentLongTermNonPromotionalDebt
 * @param currentLongTermCreditLimit
 * @param currentLongTermCreditUtilisation
 * @param changeInLongTermDebt
 * @param numPositiveScoreFactors
 * @param numNegativeScoreFactors
 * @param equifaxScoreBand
 * @param equifaxScoreBandDescription
 * @param daysUntilNextReport
 */
data class CreditReportInfo(
    val score: Int,
    val scoreBand: Int,
    val clientRef: String,
    val status: String,
    val maxScoreValue: Int,
    val minScoreValue: Int,
    val monthsSinceLastDefaulted: Int,
    val hasEverDefaulted: Boolean,
    val monthsSinceLastDelinquent: Int,
    val hasEverBeenDelinquent: Boolean,
    val percentageCreditUsed: Int,
    val percentageCreditUsedDirectionFlag: Int,
    val changedScore: Int,
    val currentShortTermDebt: Int,
    val currentShortTermNonPromotionalDebt: Int,
    val currentShortTermCreditLimit: Int,
    val currentShortTermCreditUtilisation: Int,
    val changeInShortTermDebt: Int,
    val currentLongTermDebt: Int,
    val currentLongTermNonPromotionalDebt: Int,
    val currentLongTermCreditLimit: Unit,
    val currentLongTermCreditUtilisation: Unit,
    val changeInLongTermDebt: Int,
    val numPositiveScoreFactors: Int,
    val numNegativeScoreFactors: Int,
    val equifaxScoreBand: Int,
    val equifaxScoreBandDescription: String,
    val daysUntilNextReport: Int
)