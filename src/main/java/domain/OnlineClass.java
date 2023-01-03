package domain;

import java.util.Optional;

public class OnlineClass {

    private Integer id;

    private String title;

    private boolean closed;


//    public Optional<Progress> progress;  이것도 좋지않다 설계문제
    public Progress progress;

    public OnlineClass(Integer id, String title, boolean closed) {
        this.id = id;
        this.title = title;
        this.closed = closed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    //optional은 return에 쓰는게 대부분이다
    public Optional<Progress> getProgress() {
        // stack trace도 에러다
//        if(this.progress == null) {
//            throw new IllegalStateException();
//        }

        return Optional.ofNullable(progress);
//        return Optional.empty(); 진짜 return 할께 없으면 이렇게 return 하지 null return은 하지말자
    }

    //parameter로 쓸 수 있는데 결국 값 체크를 optional 방식으로 또 해야함
    //null를 직접 넣어버리는 경우가 있기 때문에 아무튼 쓰지마라
//    public void setProgress(Optional<Progress> progress) {
//        progress.ifPresent(x -> this.progress = x);
//    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }
}
