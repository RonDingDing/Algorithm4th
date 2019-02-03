from random import shuffle


class Quick:
    def __init__(self, array):
        shuffle(array)
        self.sort(array, 0, len(array) - 1)

    def sort(self, array, low, high):
        if high <= low:
            return
        seperate = self.partition(array, low, high)
        if (self.less(array, low, seperate) and self.less(array, seperate + 1, high)):
            return
        self.sort(array, low, seperate)
        self.sort(array, seperate + 1, high)

    def partition(self, array, low, high):
        pivot, i = low, low
        j = high
        while True:
            while self.less(array, i, pivot):
                if i == high:
                    break
                i += 1
            while self.less(array, pivot, j):
                if j == low:
                    break
                j -= 1
            if i >= j:
                break
            self.exch(array, i, j)
        self.exch(array, low, j)
        return j

    def less(self, array, a, b):
        return array[a] < array[b]

    def exch(self, array, a, b):
        array[a], array[b] = array[b], array[a]


if __name__ == "__main__":


    a = list(range(88))

    shuffle(a)
    Quick(a)
    print(a)
