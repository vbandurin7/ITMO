while (<>) {
    s/\([\w\d\s(]+\)/\(\)/g ;
    print;
}
