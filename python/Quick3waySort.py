from random import shuffle


class Quick3way:
    def __init__(self, array):

        self.sort(array, 0, len(array) - 1)

    def sort(self, array, low, high):
        if high <= low:
            return
        v = array[low]
        lt = low
        i = low + 1
        gt = high

        while i <= gt:
            cmp = self.real_compare(array[i], v)
            if cmp < 0:
                self.exch(array, lt, i)
                lt += 1
                i += 1

            elif cmp > 0:
                self.exch(array, i, gt)
                gt -= 1
            elif cmp == 0:
                i += 1
        self.sort(array, low, lt - 1)
        self.sort(array, gt + 1, high)

    def real_compare(self, a, b):
        if a > b:
            return 1
        elif a < b:
            return -1
        else:
            return 0

    def equal(self, array, a, b):
        return array[a] == array[b]

    def greater(self, array, a, b):
        return array[a] > array[b]

    def exch(self, array, a, b):
        array[a], array[b] = array[b], array[a]


if __name__ == "__main__":
    from random import randint

    a = list(range(randint(1, 100)))

    shuffle(a)
    Quick3way(a)
    print(a)
