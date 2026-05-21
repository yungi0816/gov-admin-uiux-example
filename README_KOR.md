# Public Admin GIS Modernization

[English README](README.md)

공공 행정 업무시스템의 지도 기반 업무 흐름 개선과 레거시 UI 현대화 사례를 정리한 저장소입니다.

원본 운영 시스템을 공개하지 않고, 화면 구조, 사용자 흐름, 설계 판단, 리뷰 문서, 익명화된 코드 흐름만 남깁니다. 실제 테이블명, API명, 기관명, 업무 코드, 필지 식별자, 운영 데이터는 제거했습니다.

## 기술 근거

| 영역 | 근거 |
| --- | --- |
| Java/JSP 레거시 개선 | JSP, jQuery, Java Controller/Service/Repository, SQL Mapper 제약 안에서 개선 구조를 문서화 |
| GIS 기반 업무 흐름 | OpenLayers 기반 필지 선택, 도면 확인, 경계조정 검증 흐름을 별도 사례로 정리 |
| 공공/행정 시스템 이해 | 권한, 감사 로그, 기존 공통 UI, 고정 업무 절차를 설계 제약으로 다룸 |
| 실사용자 중심 UX | Before/After 이미지와 리뷰 문서로 확인 비용, 입력 오류, 업무 흐름 단절을 설명 |
| 보안 의식 | 원본 식별자를 제거한 공개용 소스와 익명화 기준 문서 포함 |

## 사례 목록

| Case | 주제 | 핵심 개선 | 자료 |
| --- | --- | --- | --- |
| 01 | 상담관리 및 지도 선택 흐름 | 상담 화면을 업무 맥락 중심으로 재정리하고 지도 기반 선택 흐름을 보강 | [cases/consult-management](cases/consult-management/README.md) |
| 02 | 경계조정 파일-필지 연계 검증 | 파일과 필지를 함께 등록하고 조정 횟수를 도면 위에서 확인 | [cases/boundary-adjustment](cases/boundary-adjustment/README.md) |

## 기술 검토 포인트

| 영역 | 설명 |
| --- | --- |
| 업무 흐름 분석 | 현업 담당자가 실제로 확인해야 하는 정보와 오류 가능성이 높은 지점을 기준으로 화면 재배치 |
| 지도/GIS 연계 | 도면, 필지 목록, 등록 파일 상태를 연결해 시각적 검증 흐름 구성 |
| 레거시 호환 | 기존 JSP 화면, 공통 CSS, 테이블 UI, 권한 처리, 파일 저장 흐름을 유지하며 개선 |
| 계층 분리 | 화면, 컨트롤러, 서비스, 데이터 접근 계층의 책임을 공개용 코드로 분리해 설명 |
| 공개 보안 | 운영 테이블명, API 경로, 업무 코드, 필지번호를 익명화 |

## 공개 기준

국가/공공 시스템 성격상 아래 항목은 공개 저장소에 남기지 않습니다.

| 구분 | 공개 저장소 처리 방식 |
| --- | --- |
| 실제 테이블, 뷰, 스키마명 | `EXAMPLE_*` 또는 역할 중심 명칭으로 치환 |
| 실제 API 경로, 업무 코드 | `/example/...` 형태의 샘플 경로로 치환 |
| 실제 패키지명, 시스템 약어 | `gov.example` 계열 샘플 패키지로 치환 |
| 기관명, 사용자명, 필지 고유번호 | 샘플 데이터 또는 설명 문장으로 대체 |
| 내부 지도/공통 유틸 구현 | 호출 목적만 남기고 구현 세부는 제거 |

자세한 기준은 [docs/security-redaction.md](docs/security-redaction.md)를 기준으로 관리합니다.

## 저장소 구조

```text
.
|-- README.md
|-- README_KOR.md
|-- docs/
|   |-- README.md
|   |-- review-framework.md
|   `-- security-redaction.md
`-- cases/
    |-- consult-management/
    |   |-- README.md
    |   |-- assets/
    |   `-- source-sanitized/
    `-- boundary-adjustment/
        |-- README.md
        |-- assets/
        `-- source-sanitized/
```

## 리뷰 방식

각 사례는 단순 화면 캡처가 아니라 아래 순서로 정리합니다.

1. 업무 문제와 사용자 리스크
2. 변경이 금지되거나 제한된 시스템 조건
3. 그 제약 안에서 선택한 UI/UX 판단
4. 프론트엔드, 서버, 데이터 흐름의 역할 분리
5. 공개 가능한 코드 구조와 비공개 처리 기준
6. 앞으로 제약이 풀릴 때 개선할 수 있는 후속 과제

이 기준은 [docs/review-framework.md](docs/review-framework.md)에 정리되어 있습니다.

## 이미지 추가 규칙

각 사례의 `assets/` 폴더에 지정된 파일명으로 이미지를 추가하면 README의 갤러리에서 바로 확인할 수 있습니다.

경계조정 사례는 다음 파일명을 사용합니다.

| 파일명 | 용도 |
| --- | --- |
| `01-boundary-overview.png` | 파일 등록, 도면, 선택 필지 목록이 함께 보이는 전체 화면 |
| `02-adjustment-legend.png` | 경계조정 횟수 색상 범례와 투명도 조정 |
| `03-linked-parcels.png` | 등록 파일 선택 후 연결 필지가 도면과 목록에 표시되는 상태 |
| `04-edit-mode.png` | 조회 상태에서 수정 모드로 전환해 필지 목록을 갱신하는 상태 |

## 현재 상태

- 상담관리 사례: 기존 Before/After 이미지와 공개용 구조 정리 완료
- 경계조정 사례: 리뷰 문서와 익명화 코드 구조 추가 완료
- 변경 이력, 라이선스 문서 추가
- 실제 업무 데이터, 운영 테이블명, 내부 API명은 포함하지 않음
