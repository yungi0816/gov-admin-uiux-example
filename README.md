
# 국가 시스템 UI/UX 혁신 예시

# 10년 이상된 국가 행정 시스템의 UI/UX 개선 사례

10년 이상 운영된 국가 행정 시스템에 최신 웹 기술과 UI/UX 패턴을 접목하여, 성능과 사용성을 동시에 개선한 사례입니다.
기존 레거시 환경의 한계를 극복하고, 실사용자 중심의 인터페이스와 현대적 개발환경을 도입했습니다.

# 국가 행정 시스템 UI/UX 개선 예시

현 예시는 10년 이상 운영된 레거시 행정 시스템의 화면을 현대적 UI/UX로 개선한 사례입니다. 핵심은 "실사용자 중심의 단순화"와 "지도 기반 상호작용 도입"입니다. 아래 스크린샷 갤러리는 로컬 `images/` 폴더의 실제 스크린샷(`before_1..3`, `after_1..3`)을 사용합니다.

---

## 핵심 요약
- 대상: 레거시 행정 시스템의 상담관리 및 지도 기반 기능
- 목적: 사용성 개선, 반복 업무 자동화, 시각적 탐색성 향상
- 핵심 기법: 대화형 인터페이스(챗형 상담), OpenLayers 기반 지도 선택, 리포트 자동화

## 주요 기능
- 대화형 상담관리 UI(문맥 기반 요약 및 입력 보조)
- 지도 기반 필지(토지) 선택 및 시각화
- 운영 리포트 자동 생성 템플릿
- PL/SQL 튜닝 및 쿼리 최적화 예시(문서화)

## 빠른 시작
1. Java 11+, Maven 설치 필요
2. 프로젝트 루트에서 빌드:

```bash
mvn clean package
```

3. 샘플 DB/설정은 `resources/`의 샘플 파일을 참조하세요.

---

## Before & After (스크린샷)

아래 갤러리는 로컬 `images/` 폴더에 저장된 실제 스크린샷을 사용합니다.

<table>
	<tr>
		<th align="center">Before (기존)</th>
		<th align="center">After (개선)</th>
	</tr>
	<tr>
		<td align="center" valign="top">
			<img src="images/before_1.png" alt="Before 1" width="320"/><br/>
			<small>기존 UI: 텍스트&표 중심의 복잡한 입력 화면</small>
			<br/><br/>
			<img src="images/before_2.png" alt="Before 2" width="320"/><br/>
			<small>중첩된 폼과 긴 목록</small>
			<br/><br/>
			<img src="images/before_3.png" alt="Before 3" width="320"/><br/>
			<small>지도 연동 전의 필지 선택 화면</small>
		</td>
		<td align="center" valign="top">
			<img src="images/after_1.png" alt="After 1" width="320"/><br/>
			<small>개선 UI: 대화형 상담 + 요약 카드</small>
			<br/><br/>
			<img src="images/after_2.png" alt="After 2" width="320"/><br/>
			<small>직관적 필터링과 단축 액션</small>
			<br/><br/>
			<img src="images/after_3.png" alt="After 3" width="320"/><br/>
			<small>지도에서 직접 필지 선택 및 시각화</small>
		</td>
	</tr>
</table>

> 위 이미지는 로컬의 `images/before_1.png` ~ `images/after_3.png` 파일을 참조합니다.

---

## 이미지 관련 안내
- 이 리포지토리에는 예시 스크린샷만 포함되어 있으며, 실제 민감 데이터는 제거(익명화)되어 있습니다.
- 다른 스크린샷으로 교체하려면 `images/` 폴더에 동일한 파일명으로 업로드하십시오.

## 기여 및 라이선스
- 본 예시는 교육/포트폴리오 목적의 오픈소스 예시입니다. 코드·문서 수정은 자유롭게 PR을 보내주세요.

---

> 문의: 프로젝트 구조나 샘플 데이터 제공 방법에 대해 도와드릴 수 있습니다.
