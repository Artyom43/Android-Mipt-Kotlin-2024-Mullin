package zoo

fun <K, V> MutableMap<K, MutableList<V>>.putOrAdd(key: K, value: V) {
    if (this.containsKey(key)) {
        this[key]?.add(value)
    } else {
        this[key] = mutableListOf(value)
    }
}