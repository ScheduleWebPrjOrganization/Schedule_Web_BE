package ac.su.schedule_web_prj_be.service;

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
    private SubjectRecordRepository subjectRecordRepository;

    // 타이머 시작 기능
    public SubjectRecord startTimer(Subject subject) {
        SubjectRecord record = new SubjectRecord();
        record.setSubject(subject);
        record.setRecordedAt(new Date());
        record.setStoppedAt(null);   // 아직 멈추지 않음
        record.setPausedAt(null);   // 아직 일시정지하지 않았음
        record.setPausedDuration(0);   // 일시정지된 시간을 0으로 설정
        return subjectRecordRepository.save(record);
    }

    // 타이머 중지 기능
    public SubjectRecord stopTimer(Long recordId) {
        SubjectRecord record = subjectRecordRepository.findById(recordId).orElseThrow();
        if (record.getPausedAt() != null) {   // 일시정지 되었을때 알려주는 코드
            throw new IllegalStateException("타이머는 현재 일시정지 되어 있습니다.");
        }
        record.setStoppedAt(new Date());
        return subjectRecordRepository.save(record);
    }

    // 타이머 일시정지 기능
    public SubjectRecord pauseTimer(Long recordId) {
        SubjectRecord record = subjectRecordRepository.findById(recordId).orElseThrow();
        if (record.getPausedAt() != null) {
            throw new IllegalStateException("타이머가 이미 일시정지 되어 있습니다.");
        }
        record.setPausedAt(new Date());
        return subjectRecordRepository.save(record);
    }

    // 타이머 일시정지 재개 기능
    public SubjectRecord resumeTimer(Long recordId) {
        SubjectRecord record = subjectRecordRepository.findById(recordId).orElseThrow();
        if (record.getPausedAt() == null) {
            throw new IllegalStateException("타이머가 일시정지 되어 있지 않습니다.");
        }
        long pausedTime = new Date().getTime() - record.getPausedAt().getTime();
        record.setPausedDuration(record.getPausedDuration() + pausedTime);
        record.setPausedAt(null);
        return subjectRecordRepository.save(record);
    }

    // 현재 공부 중인 과목들을 가져오는 메서드
    public List<SubjectRecord> getCurrentStudyingSubjects() {
        return subjectRecordRepository.findAll().stream()
                .filter(record -> record.getStoppedAt() == null && record.getPausedAt() == null)  // 일시정지, 정지 하지 않았을때만 가져옴
                .collect(Collectors.toList());
    }

    // 특정 과목의 누적 공부 시간을 계산하는 메서드
    public long getTotalStudyTimeForSubject(Subject subject) {
        return subjectRecordRepository.findAll().stream()
                .filter(record -> record.getSubject().getId().equals(subject.getId()))
                .mapToLong(record -> {
                    long endTime = (record.getStoppedAt() != null) ? record.getStoppedAt().getTime() : new Date().getTime();
                    return (endTime - record.getRecordedAt().getTime()) - record.getPausedDuration();  // 계산한 총 시간에서 일시정지한 시간을 뺌
                })
                .sum();
    }

    // 타이머 시간 계산
    public long getElapsedTime(Long recordId) {
        SubjectRecord record = subjectRecordRepository.findById(recordId).orElseThrow();
        if (record.getStoppedAt() == null) {
            throw new IllegalStateException("타이머를 정지해주세요");
        }
//        return record.getStoppedAt().getTime() - record.getRecordedAt().getTime();    // 일시정지 기능 넣지 않았을 때 시간을 계산하는 코드
        long totalTime = record.getStoppedAt().getTime() - record.getRecordedAt().getTime();    // 일시정지 기능 넣었을 때 시간을 계산하는 코드
        return totalTime - record.getPausedDuration();
    }
}
