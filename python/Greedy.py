states_needed = {'mt', 'wa', 'id', 'nv', 'ut', 'ca', 'az'}

stations = {
    'kone': {'id', 'nv', 'ut'},
    'ktwo': {'wa', 'id', 'mt'},
    'kthree': {'or', 'nv', 'ca'},
    'kfour': {'nv', 'ut'},
    'kfive': {'ca', 'az'}
}

final_stations = set()

while states_needed:
    best_station = None
    states_covered = set()
    for station, states_for_station in stations.items():
        covered = states_needed & states_for_station
        if len(covered) > len(states_covered):
            best_station = station
            states_covered = covered
            final_stations.add(best_station)
            states_needed -= states_covered

if __name__ == '__main__':
    print(final_stations)
