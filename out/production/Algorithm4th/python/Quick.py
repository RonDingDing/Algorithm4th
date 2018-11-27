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
    boy = low
    cat = high + 1
    v = array[low]
    while True:
        while array[boy + 1] < v:
            boy += 1
            if boy == high:
                break
        while v < array[cat - 1]:
            cat -= 1
            if cat == low:
                break
        if boy >= cat:
            break
        exch(array, boy, cat)
    exch(array, low, cat)

    return cat


if __name__ == "__main__":
    a = [2, 1, 3, 4, 5, 6]
    sorting(a)
    print(a)
