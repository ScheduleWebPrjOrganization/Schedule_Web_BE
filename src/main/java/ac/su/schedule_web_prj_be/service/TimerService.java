package ac.su.schedule_web_prj_be.service;

import ac.su.schedule_web_prj_be.domain.Task;
import ac.su.schedule_web_prj_be.exception.ResourceNotFoundException;
import ac.su.schedule_web_prj_be.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ac.su.schedule_web_prj_be.domain.Subject;
import ac.su.schedule_web_prj_be.domain.SubjectRecord;
import ac.su.schedule_web_prj_be.repository.SubjectRecordRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimerService {

    private long startTime = 0;
    private long pauseTime = 0;
    private long totalPausedTime = 0;
    private boolean isPaused = false;
    private SubjectRecord record;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private SubjectRecordRepository subjectRecordRepository;

    public TimerService() {
        this.record = new SubjectRecord();
    }
    // 타이머 시작
    public void startTimer(Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id " + subjectId));
        // Subject에 대한 타이머 시작 로직
        record = new SubjectRecord(); // 새 SubjectRecord 인스턴스 생성
        startTime = System.currentTimeMillis(); // UTC(GMT) 기준으로 시간이 저장됨
        record.setRecordedAt(new Date(startTime));  // 시작을 누른 타이밍의 시간 기록
        record.setSubject(subject); // SubjectRecord에 Subject 참조 설정
//        subjectRecordRepository.save(record); // 변경된 상태 저장, stopped_at이 null을 허용하지 않으므로 주석처리
        totalPausedTime = 0;  // 새로 시작할 때 일시 정지 시간 초기화
        isPaused = false;
    }

    // 타이머 일시 정지
    public void pauseTimer() {
        if (!isPaused) {
            pauseTime = System.currentTimeMillis();
            record.setPausedAt(new Date(pauseTime));  // 일시 정지를 누른 타이밍의 시간 업데이트
//            subjectRecordRepository.save(record); // 변경된 상태 저장, stopped_at이 null을 허용하지 않으므로 주석처리
            isPaused = true;
        } else {
            throw new IllegalStateException("타이머가 이미 일시정지 되어 있습니다.");
        }
    }

    // 타이머 재개
    public void resumeTimer() {
        if (isPaused) {
            long currentPauseDuration = System.currentTimeMillis() - pauseTime;
            totalPausedTime += currentPauseDuration;
            record.setPausedDuration(totalPausedTime);  // 총 일시 정지 시간 업데이트
//            subjectRecordRepository.save(record); // 변경된 상태 저장, stopped_at이 null을 허용하지 않으므로 주석처리
            isPaused = false;
        } else {
            throw new IllegalStateException("타이머가 일시정지 되어 있지 않습니다.");
        }
    }

    // 타이머 정지 및 총 시간 계산 (밀리초로 나오는 시간을 시간:분:초로 보여주기 위해 String 타입 사용)
    public String stopTimer(Long subjectId) {
        if (startTime == 0) {
            throw new IllegalStateException("타이머를 시작하지 않았습니다.");
        }
        if (isPaused) {
            resumeTimer();  // 일시 정지 상태에서 중지를 요청할 경우, 자동으로 재개
        }
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id " + subjectId));
        // Subject에 대한 타이머 정지 및 시간 형식화 로직
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime - totalPausedTime;
        record.setStoppedAt(new Date(endTime));  // 정지를 누른 타이밍의 시간을 업데이트
        subjectRecordRepository.save(record); // 지금까지 변경된 상태를 DB에 저장
        startTime = 0;  // 타이머 리셋
        return formatTime(elapsedTime);
    }

    // 밀리초를 시간:분:초 형식으로 변환
    private String formatTime(long millis) {
        long seconds = (millis / 1000) % 60;
        long minutes = (millis / (1000 * 60)) % 60;
        long hours = (millis / (1000 * 60 * 60)) % 24;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public void deleteRecordBySubjectId(Long subjectId) {
        List<SubjectRecord> records = subjectRecordRepository.findBySubjectId(subjectId);
        subjectRecordRepository.deleteAll(records);
    }
}
//@Service
//public class TimerService {
//    private SubjectRecordRepository subjectRecordRepository;
//
//    // 타이머 시작 기능
//    public SubjectRecord startTimer(Subject subject) {
//        SubjectRecord record = new SubjectRecord();
//        record.setSubject(subject);
//        record.setRecordedAt(new Date());
//        record.setStoppedAt(null);   // 아직 멈추지 않음
//        record.setPausedAt(null);   // 아직 일시정지하지 않았음
//        record.setPausedDuration(0);   // 일시정지된 시간을 0으로 설정
//        return subjectRecordRepository.save(record);
//    }
//
//    // 타이머 중지 기능
//    public SubjectRecord stopTimer(Long recordId) {
//        SubjectRecord record = subjectRecordRepository.findById(recordId).orElseThrow();
//        if (record.getPausedAt() != null) {   // 일시정지 되었을때 알려주는 코드
//            throw new IllegalStateException("타이머는 현재 일시정지 되어 있습니다.");
//        }
//        record.setStoppedAt(new Date());
//        return subjectRecordRepository.save(record);
//    }
//
//    // 타이머 일시정지 기능
//    public SubjectRecord pauseTimer(Long recordId) {
//        SubjectRecord record = subjectRecordRepository.findById(recordId).orElseThrow();
//        if (record.getPausedAt() != null) {
//            throw new IllegalStateException("타이머가 이미 일시정지 되어 있습니다.");
//        }
//        record.setPausedAt(new Date());
//        return subjectRecordRepository.save(record);
//    }
//
//    // 타이머 일시정지 재개 기능
//    public SubjectRecord resumeTimer(Long recordId) {
//        SubjectRecord record = subjectRecordRepository.findById(recordId).orElseThrow();
//        if (record.getPausedAt() == null) {
//            throw new IllegalStateException("타이머가 일시정지 되어 있지 않습니다.");
//        }
//        long pausedTime = new Date().getTime() - record.getPausedAt().getTime();
//        record.setPausedDuration(record.getPausedDuration() + pausedTime);
//        record.setPausedAt(null);
//        return subjectRecordRepository.save(record);
//    }
//
//    // 현재 공부 중인 과목들을 가져오는 메서드
//    public List<SubjectRecord> getCurrentStudyingSubjects() {
//        return subjectRecordRepository.findAll().stream()
//                .filter(record -> record.getStoppedAt() == null && record.getPausedAt() == null)  // 일시정지, 정지 하지 않았을때만 가져옴
//                .collect(Collectors.toList());
//    }
//
//    // 특정 과목의 누적 공부 시간을 계산하는 메서드
//    public long getTotalStudyTimeForSubject(Subject subject) {
//        return subjectRecordRepository.findAll().stream()
//                .filter(record -> record.getSubject().getId().equals(subject.getId()))
//                .mapToLong(record -> {
//                    long endTime = (record.getStoppedAt() != null) ? record.getStoppedAt().getTime() : new Date().getTime();
//                    return (endTime - record.getRecordedAt().getTime()) - record.getPausedDuration();  // 계산한 총 시간에서 일시정지한 시간을 뺌
//                })
//                .sum();
//    }
//
//    // 타이머 시간 계산
//    public long getElapsedTime(Long recordId) {
//        SubjectRecord record = subjectRecordRepository.findById(recordId).orElseThrow();
//        if (record.getStoppedAt() == null) {
//            throw new IllegalStateException("타이머를 정지해주세요");
//        }
////        return record.getStoppedAt().getTime() - record.getRecordedAt().getTime();    // 일시정지 기능 넣지 않았을 때 시간을 계산하는 코드
//        long totalTime = record.getStoppedAt().getTime() - record.getRecordedAt().getTime();    // 일시정지 기능 넣었을 때 시간을 계산하는 코드
//        return totalTime - record.getPausedDuration();
//    }
//}
