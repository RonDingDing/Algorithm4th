class Heap:
    def __init__(self):
        pass

    def sort(self, array):
        n = len(array)
        k = n // 2
        while k >= 1:
            self.sink(array, k, n)
            k -= 1
        while n > 1:
            self.exch(array, 1, n)
            n -= 1
            self.sink(array, 1, n)

    def sink(self, array, k, n):
        while (2 * k <= n):
            j = 2 * k
            if j < n and self.less(array, j, j + 1):
                j += 1
            if not self.less(array, k, j):
                break
            self.exch(array, k, j)
            k = j

    def less(self, array, i, j):
        return array[i - 1] < array[j - 1]

    def exch(self, array, i, j):
        array[i - 1], array[j - 1] = array[j - 1], array[i - 1]


if __name__ == "__main__":
    from random import shuffle
    heap = Heap()
    a = list(range(8))
    shuffle(a)
    heap.sort(a)
    print(a)
