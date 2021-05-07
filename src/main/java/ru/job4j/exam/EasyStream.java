package ru.job4j.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class EasyStream {
    private List<Integer> source;

    static class Builder {
        private List<Integer> source;

        Builder buildSource(List<Integer> source) {
            this.source = source;
            return this;
        }

        EasyStream build() {
            EasyStream easyStream = new EasyStream();
            easyStream.source = source;
            return easyStream;
        }
    }

    public static EasyStream of(List<Integer> source) {
        return new Builder()
                .buildSource(source)
                .build();
    }

    public EasyStream map(Function<Integer, Integer> fun) {
        List<Integer> tmp = new ArrayList<>();
        for (var elem : source) {
            tmp.add(fun.apply(elem));
        }
        source = tmp;
        return this;
    }

    public EasyStream filter(Predicate<Integer> fun) {
        List<Integer> tmp = new ArrayList<>();
        for (Integer elem : source) {
            if (fun.test(elem)) {
                tmp.add(elem);
            }
        }
        source = tmp;
        return this;
    }

    public List<Integer> collect() {
        return List.copyOf(source);
    }
}
