def quicksort(array):
    if len(array) < 2:
        return array
    else:
        pivot = array[0]
        less = [i for i in array[1:] if i <= pivot]
        greater = [i for i in array[1:] if i > pivot]
        return quicksort(less) + [pivot] + quicksort(greater)


def exch(array, i, j):
    array[i], array[j] = array[j], array[i]


def sorting(array):
    quicksort2(array, 0, len(array) - 1)


def quicksort2(array, low, high):
    if high <= low: return
    j = partition(array, low, high)
    quicksort2(array, low, j - 1)
    quicksort2(array, j + 1, high)


def partition(array, low, high):
    v = array[low]
    i = low + 1
    j = high

    while True:
        while array[i] < v:
            i += 1
            if i >= high:
                break
        while v < array[j]:
            j -= 1
            if j <= low:
                break

        if i >= j:
            break

        exch(array, i, j)
    exch(array, low, j)

    return j


if __name__ == "__main__":
    import random
    a = [2, 1, 3, 5, 4, 6]
    random.shuffle(a)
    sorting(a)
    print(a)
