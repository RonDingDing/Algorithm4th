class Shell:
    def __init__(self, array):
        self.sort(array)

    def sort(self, array):
        h = 1
        while h < len(array) // 3:
            h = 3 * h + 1
        while h >= 1:
            for i in range(h, len(array)):
                for j in range(i, 0, -h):
                    if j >= h and self.less(array, j, j - h):
                        self.exch(array, j, j - h)
            h //= 3

        return array

    def less(self, array, a, b):
        return array[a] < array[b]

    def exch(self, array, a, b):
        array[a], array[b] = array[b], array[a]


if __name__ == "__main__":
    a = list(range(18))
    from random import shuffle

    shuffle(a)
    Shell(a)
    print(a)
