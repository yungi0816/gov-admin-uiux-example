# Public Admin GIS Modernization

[한국어 README](README_KOR.md)

Case-study repository for improving map-based work flows and legacy UI structure in public-sector administration systems.

The repository does not expose the original production system. Each case keeps the workflow, design decisions, review notes, and sanitized implementation structure while removing internal table names, API names, organization identifiers, work codes, parcel identifiers, and real operational data.

## Technical Evidence

| Area | Evidence |
| --- | --- |
| Legacy Java/JSP modernization | UI improvements are documented within JSP, jQuery, Java controller/service/repository, and mapper constraints |
| GIS-enabled admin workflow | OpenLayers-based parcel selection and boundary review flows are documented as separate case studies |
| Public-sector system constraints | Audit logging, authorization, fixed business procedures, and shared UI rules are treated as design constraints |
| User-centered operation design | Before/after screenshots and review notes explain how the work reduces confirmation cost and input mistakes |
| Security-aware documentation | Sanitized source and redaction rules prevent production identifiers from being published |

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
| Changelog | [CHANGELOG.md](CHANGELOG.md) |

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

This repository is published for portfolio review. See [LICENSE](LICENSE).
