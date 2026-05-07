# 국가 행정 시스템 UI/UX 개선 예시

[English README](README.md)

10년 이상 운영된 레거시 행정 시스템의 화면을 실사용자 중심으로 재설계한 사례입니다.

핵심 목표는 기존 업무 흐름을 갑자기 뒤집지 않으면서, 상담 관리와 지도 기반 선택 작업의 사용성을 높이는 것입니다.

## 핵심 요약

- 대상: 레거시 행정 시스템의 상담관리 및 지도 기반 기능
- 주요 사용자: 40대 ~ 60대 현업 담당자
- 목적: 사용성 저항 최소화, 업무 효율화, 입력 오류 감소
- 핵심 기법: 대화형 상담 UI, OpenLayers 지도 선택, 운영 리포트 자동화

## 이런 분에게 유용합니다

- 오래된 JSP/Java 기반 행정 시스템을 점진적으로 개선해야 하는 분
- 현업 사용자의 거부감을 낮추는 UI/UX 설계 사례가 필요한 분
- Before/After 스크린샷과 함께 포트폴리오형 개선 사례를 정리하고 싶은 분

## 주요 기능

- 대화형 상담관리 UI: 간단한 문장 입력으로 업무 흐름 유도
- 지도 기반 필지 선택: 시각적 선택으로 오류 감소
- 운영 리포트 자동 생성: 반복 업무 자동화로 시간 절감
- 성능 가이드: PL/SQL 튜닝 및 쿼리 최적화 예시

## 빠른 시작

1. Java 11+ 및 Maven을 설치합니다.
2. 프로젝트 루트에서 빌드합니다.

```bash
mvn clean package
```

3. 샘플 DB/설정은 `resources/`의 샘플 파일을 확인하세요.

## Before & After

<table>
  <tr>
    <th align="center">Before</th>
    <th align="center">After</th>
  </tr>
  <tr>
    <td align="center" valign="top">
      <img src="images/before_1.png" alt="Before 1" width="320"/><br/>
      <small>텍스트와 표 중심의 복잡 화면</small>
      <br/><br/>
      <img src="images/before_2.png" alt="Before 2" width="320"/><br/>
      <small>중첩 폼과 긴 목록</small>
      <br/><br/>
      <img src="images/before_3.png" alt="Before 3" width="320"/><br/>
      <small>지도 미연동 상태</small>
    </td>
    <td align="center" valign="top">
      <img src="images/after_1.png" alt="After 1" width="320"/><br/>
      <small>대화형 상담 + 핵심 정보 요약</small>
      <br/><br/>
      <img src="images/after_2.png" alt="After 2" width="320"/><br/>
      <small>간결한 필터와 단축 액션</small>
      <br/><br/>
      <img src="images/after_3.png" alt="After 3" width="320"/><br/>
      <small>지도 기반 직접 선택</small>
    </td>
  </tr>
</table>

## 디자인 원칙

- 기본 텍스트는 16~18px 이상으로 유지
- 버튼과 컨트롤은 최소 44~48px 높이로 설계
- 명확한 레이블과 단계적 흐름 제공
- 텍스트와 배경 대비 확보
- 오류 메시지는 원인과 다음 행동을 함께 안내

## 의견과 기여

- 버그나 실행 문제는 [Issues](https://github.com/yungi0816/gov-admin-uiux-example/issues)에 남겨주세요.
- 공공/레거시 UI 개선 아이디어나 화면 설계 피드백은 [Discussions](https://github.com/yungi0816/gov-admin-uiux-example/discussions)에 남겨주세요.
- 문서, 접근성, 스크린샷 설명 개선 PR은 환영합니다.
