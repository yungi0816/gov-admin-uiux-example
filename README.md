# Government Admin UI/UX Modernization Examples

[한국어 README](README_KOR.md)

This repository documents UI/UX improvement work for legacy public-sector administration systems. It does not expose the original production system. Each case keeps the workflow, design decisions, and sanitized implementation structure while removing internal table names, API names, organization identifiers, and real operational data.

The goal is not a full visual redesign. These examples show how to improve task accuracy and reviewability inside strict constraints: JSP/Java screens, shared legacy CSS, audit logging, authorization rules, existing map components, and fixed business procedures.

## Cases

| Case | Topic | Improvement Focus | Document |
| --- | --- | --- | --- |
| 01 | Consultation management and map selection | Reorganized consultation flow and strengthened map-based selection | [cases/consult-management](cases/consult-management/README.md) |
| 02 | Boundary adjustment file and parcel review | Registers files with linked parcels and visualizes adjustment counts on the map | [cases/boundary-adjustment](cases/boundary-adjustment/README.md) |

## Documentation

| Area | Document |
| --- | --- |
| Documentation index | [docs/README.md](docs/README.md) |
| Review framework | [docs/review-framework.md](docs/review-framework.md) |
| Security redaction policy | [docs/security-redaction.md](docs/security-redaction.md) |

## Repository Structure

```text
.
|-- README.md
|-- README_KOR.md
|-- docs/
`-- cases/
    |-- consult-management/
    `-- boundary-adjustment/
```

## Redaction Standard

Production table names, API paths, work codes, schema names, package names, organization names, user identifiers, parcel identifiers, and internal map utilities are replaced with role-based sample names. The sanitized source files are intended to explain architecture and review decisions, not to run against a real environment.

## Image Workflow

Each case owns its screenshots under `cases/<case-name>/assets/`. Add images using the filenames documented in each case README and the gallery will render directly from the README.

## License

No explicit license is currently provided. Treat the repository as a portfolio-style documentation example unless a license is added.
