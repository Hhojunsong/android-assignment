package com.mca.androidseminar2026.assignment

import com.mca.androidseminar2026.model.SaveReviewResult
import com.mca.androidseminar2026.model.ContentListItemUiModel

/**
 * [과제 구현 파일 1]
 *
 * RawContents의 원시 데이터를 분석해 앱의 데이터 모델을 직접 설계하세요.
 * 필요한 data class, enum class, 인터페이스, 상속 구조, 컬렉션을 이 파일에 자유롭게 추가할 수 있습니다.
 * search, saveReview, clearReviews 함수의 형태를 수정할 때에는 해당 함수를 호출하는 곳도 함께 수정하세요.
 */
class ShelfLogAssignment(rawCatalog: List<Map<String, String>>) {

    // TODO 1. 원시 데이터인 rawCatalog를 앱에서 사용할 작품 목록으로 변환해 프로퍼티로 보관하세요.
    // TODO 3~5의 검색, 저장, 전체 삭제 함수가 이 프로퍼티를 사용할 수 있어야 합니다.
    // 클래스를 새로 정의해서 사용해 주세요.
    // 단, 정의하신 클래스의 id는 반드시 Int 타입으로 해 주세요.
    sealed class Content {
        abstract val id: Int
        abstract val title: String
        abstract val year: String


        data class Movie(
            override val id: Int,
            override val title: String,
            val director: String,
            override val year: String,
            val runningTimeMinutes: Int,
        ) : Content()

        data class Book(
            override val id: Int,
            override val title: String,
            val author: String,
            override val year: String,
            val pageCount: Int,
        ) : Content()
    }
    // 예:
    private val contents: MutableList<Content>

    init {
        contents = rawCatalog.map { raw -> toContent(raw) }.toMutableList()
    }

    private fun toContent(raw: Map<String, String>): Content{
        return when (raw["kind"]){
            "movie" -> Content.Movie(
                id = raw["id"]!!.toInt(),
                title = raw["title"]!!,
                director = raw["director"]!!,
                year = raw["year"]!!,
                runningTimeMinutes = raw["runningTimeMinutes"]!!.toInt()
            )

            "book" -> Content.Book(
                id = raw["id"]!!.toInt(),
                title = raw["title"]!!,
                author = raw["author"]!!,
                year = raw["year"]!!,
                pageCount = raw["pageCount"]!!.toInt()
            )

            else -> error("contents error")
        }
    }

    // TODO 2. 작품별 감상 기록을 저장하는 방식을 결정하세요.
    // 각 기록에는 평점과 메모가 필요합니다.
    //
    // 아래 방식 중 하나를 선택하거나, 다른 방식을 사용해도 됩니다.
    // - 위에서 만든 작품 객체 안에 감상 기록을 포함한다.
    // - 작품 ID를 프로퍼티로 가지는, 감상 기록 저장용 객체를 새로 만든다.
    data class Review(
        val contentId: Int,
        val rating: String,
        val memo: String?
    )

    /** 검색 결과를 ContentListItemUiModel로 변환해 반환하세요. */
    fun search(query: String): List<ContentListItemUiModel> {
        // TODO 3.
        // 1단계: 검색어(query)로 필터링하기
        val filteredContents = contents.filter { content ->
            if (query.isBlank()) {
                true
            }
            else if(content.title.contains(query, ignoreCase = true)){
                true
            }
            else{
                when (content){
                    is Content.Movie -> {
                        content.director.contains(query, ignoreCase = true)
                    }
                    is Content.Book -> {
                        content.author.contains(query, ignoreCase = true)
                    }
                }
            }
        }

        // 2단계: ID 오름차순 정렬하기
        val sortedContents = filteredContents.sortedBy { content ->
            content.id
        }

        // 3단계: Content를 ContentListItemUiModel로 변환하기
        return sortedContents.map { content ->
            when (content) {
                is Content.Movie -> {
                    ContentListItemUiModel(
                        id = content.id,
                        typeLabel = "Movie",
                        title = content.title,
                        creator = content.director,
                        year = content.year,
                        pageCount = null,
                        runningTimeMinutes = content.runningTimeMinutes,
                        reviewSummary = ""
                    )
                }
                is Content.Book -> {
                    ContentListItemUiModel(
                        id = content.id,
                        typeLabel = "Book",
                        title = content.title,
                        creator = content.author,
                        year = content.year,
                        pageCount = content.pageCount,
                        runningTimeMinutes = null,
                        reviewSummary = ""
                    )
                }
            }
        }
    }

    /** 입력을 검증하고 감상 기록을 추가하거나 기존 기록을 갱신하세요. */
    fun saveReview(
        contentId: Int,
        ratingText: String,
        memo: String,
    ): SaveReviewResult {
        TODO("TODO 4. 평점을 검증하고, 같은 작품의 기록은 갱신하세요.")
    }

    /** 모든 작품에서 감상 기록을 지우세요. */
    fun clearReviews() {
        TODO("TODO 5. 모든 작품에서 감상 기록을 지우세요.")
    }
}
