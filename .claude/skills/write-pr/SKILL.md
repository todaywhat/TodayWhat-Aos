---
name: write-pr
description: >
  현재 브랜치의 변경 사항을 분석하여 GitHub Draft PR을 작성·생성합니다.
  "PR 만들어줘", "PR 작성", "draft PR 올려줘", "이 브랜치 PR 생성" 등을 말할 때 트리거.
  현재 브랜치에 대한 PR이 이미 존재하면 새로 작성한 내용으로 수정합니다.
allowed-tools:
  - Read
  - Write
  - Edit
  - Bash
  - Grep
  - Glob
---

# PR 작성 및 생성 (Draft)

현재 브랜치의 변경 사항을 분석하여 GitHub **Draft PR**을 자동으로 생성합니다.
현재 브랜치에 대한 PR이 이미 존재하면, 새로 작성한 내용으로 **수정**합니다.

## 작업 순서

### 0. GitHub CLI 권한 확인
`gh` CLI가 설치·인증되어 있고 현재 저장소에 접근 가능한지 확인한다.
```bash
command -v gh        # 없으면: brew install gh
gh auth status       # 인증 필요 시: gh auth login
gh repo view         # 저장소 접근 확인
```
인증/로그인은 사용자가 직접 해야 하므로, 필요하면 안내만 하고 멈춘다.

### 1. base 브랜치 자동 감지
이 저장소는 스크립트가 없으므로 Claude가 직접 base 브랜치를 추론한다.

- 현재 브랜치 확인: `git branch --show-current`
- 후보 base 브랜치(`develop`, `master`, `main`) 각각에 대해 분기 지점 이후 커밋 수를 비교해, **가장 가까운(고유 커밋 수가 가장 적은) 브랜치**를 base로 택한다.
```bash
git fetch origin --quiet
for b in develop master main; do
  git rev-parse --verify --quiet "origin/$b" >/dev/null || continue
  mb=$(git merge-base HEAD "origin/$b")
  cnt=$(git rev-list --count "$mb"..HEAD)
  echo "$cnt $b"
done | sort -n | head -1 | cut -d' ' -f2
```
**규칙**: 이렇게 감지한 base 브랜치를 신뢰하고 그대로 사용한다. 사용자의 요청이 없는 한 develop/master 등으로 임의 교체하지 않는다. (릴리스 브랜치는 보통 master를 향하는 등 develop이 아닐 수 있다.)

### 2. 변경 사항 분석
base 브랜치(`$BASE`)와의 차이로 PR 본문 정보를 수집한다.
```bash
git diff origin/$BASE...HEAD --name-status   # 변경 파일
git diff origin/$BASE...HEAD --stat          # 변경 통계
git log  origin/$BASE..HEAD --oneline        # 커밋 히스토리
```
필요하면 핵심 변경 파일의 실제 diff를 읽어 정확한 내용을 파악한다.

### 3. PR 본문 작성
아래 템플릿으로 작성한다. **실제 변경 사항만** 기술하고, 없는 내용·코드를 추측해서 만들어내지 않는다. 변경 성격에 따라 선택 섹션을 가감한다.

- 버그/크래시 수정이면 `## 🔍 원인 (Root Cause)`를 추가한다.
- 수동 검증이 필요하면 `## 🧪 Test plan` 체크리스트를 추가한다.
- 커밋이 여러 개면 `### 커밋 이력`을 `🔀 변경사항` 아래 둔다.

#### 본문 템플릿
````markdown
## 💡 개요
- 이 PR이 무엇을/왜 하는지 1~3줄 요약

## 🔍 원인 (Root Cause)   ← 버그·크래시 수정 시에만
스택 트레이스 핵심:
```
...
```
원인 설명

## 📃 작업내용
- 한 일 요약 (목적 + 내용)

## 🔀 변경사항
- `path/to/File.kt` — 무엇을 어떻게 바꿨는지

### 커밋 이력   ← 커밋이 여러 개일 때
- `<hash>` <커밋 메시지>

## 🧪 Test plan   ← 검증 항목이 있을 때
- [ ] 수동 검증 항목
- [x] `:<module>:compileDebugKotlin` 통과

## 🎸 기타
- 참고/주의/후속 작업 등 (없으면 섹션 생략)

🤖 Generated with [Claude Code](https://claude.com/claude-code)
````

### 4. PR 제목
저장소 커밋/PR 컨벤션(gitmoji + 한국어)을 따른다: `<emoji> :: <설명>`.
관련 이슈 번호가 있으면 `<emoji> :: (#<번호>) - <설명>` 형식을 사용한다.
대표 커밋의 emoji/요지를 활용해 제목을 정한다. (예: `🐛 :: 위젯 클릭 시 Glance ActionTrampoline 크래시 방지`)

### 5. Draft PR 생성 또는 수정
본문을 임시 파일로 저장한 뒤, 기존 PR 유무에 따라 분기한다.
```bash
PR_BODY_FILE=$(mktemp)
cat > "$PR_BODY_FILE" <<'EOF'
<위에서 작성한 본문>
EOF

# 현재 브랜치에 열린 PR이 있는지 확인
EXISTING=$(gh pr list --head "$(git branch --show-current)" --state open --json number -q '.[0].number')

if [ -n "$EXISTING" ]; then
  gh pr edit "$EXISTING" --title "$PR_TITLE" --body-file "$PR_BODY_FILE"
else
  gh pr create --draft --base "$BASE" --title "$PR_TITLE" --body-file "$PR_BODY_FILE"
fi

rm -f "$PR_BODY_FILE"
```
생성/수정된 PR URL을 사용자에게 보고한다.
