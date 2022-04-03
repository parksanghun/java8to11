package kr.co.sanghun.study;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@RequiredArgsConstructor
public class OnlineClass {
    private final Integer id;
    private final String title;
    private final boolean closed;

    public Progress progress;

    public Optional<Progress> getProgress() {
        return Optional.ofNullable(progress);
    }
}
