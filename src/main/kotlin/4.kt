import Point.Companion.onSegment
import Point.Companion.orientation
import Segment.Companion.intersect

data class Point(
    val x: Int,
    val y: Int
) {
    companion object {
        fun onSegment(p1: Point, p2: Point, p3: Point) = p2.x <= maxOf(p1.x, p3.x) && p2.x >= minOf(p1.x, p3.x) &&
                p2.y <= maxOf(p1.y, p3.y) && p2.y >= minOf(p1.y, p3.y)

        fun orientation(p1: Point, p2: Point, p3: Point): Int {
            val check = (p2.y - p1.y) * (p3.x - p2.x) -
                    (p2.x - p1.x) * (p3.y - p2.y)

            if (check == 0) return 0
            if (check > 0) return 1
            return 2
        }
    }
}

data class Segment(
    val start: Point,
    val end: Point
) {
    companion object {
        private fun intersect(start1: Point, end1: Point, start2: Point, end2: Point): Boolean {
            val o1: Int = orientation(start1, end1, start2)
            val o2: Int = orientation(start1, end1, end2)
            val o3: Int = orientation(start2, end2, start1)
            val o4: Int = orientation(start2, end2, end1)

            if (o1 != o2 && o3 != o4)
                return true

            return (o1 == 0 && onSegment(start1, start2, end1) ||
                    o2 == 0 && onSegment(start1, end2, end1) ||
                    o3 == 0 && onSegment(start2, start1, end2) ||
                    o4 == 0 && onSegment(start2, end1, end2))
        }

        fun intersect(s1: Segment, s2: Segment): Boolean {
            return intersect(s1.start, s1.end, s2.start, s2.end)
        }
    }
}

fun main() {
    val (x1, y1, x2, y2) = readln().split(" ").map { it.toInt() }
    val road = Segment(Point(x1, y1), Point(x2, y2))
    val n = readln().toInt()
    val rivers = emptyList<Segment>().toMutableList()
    repeat(n) {
        val (x1, y1, x2, y2) = readln().split(" ").map { it.toInt() }
        rivers.add(Segment(Point(x1, y1), Point(x2, y2)))
    }
    println(rivers.filter { intersect(road, it) }.size)
}

