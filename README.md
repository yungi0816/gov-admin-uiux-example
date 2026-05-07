# Government Admin UI/UX Modernization Example

[한국어 README](README_KOR.md)

[![Java](https://img.shields.io/badge/Java-ED8B00?style=flat-square)](https://www.java.com/)
[![Maven](https://img.shields.io/badge/Maven-C71A36?style=flat-square&logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![OpenLayers](https://img.shields.io/badge/OpenLayers-1F6B75?style=flat-square&logo=openlayers&logoColor=white)](https://openlayers.org/)
[![UI/UX](https://img.shields.io/badge/UI%2FUX-modernization-2563EB?style=flat-square)](https://github.com/yungi0816/gov-admin-uiux-example)

This repository is a practical before/after example for modernizing a 10+ year-old public-sector admin system.

The goal is not to redesign everything at once. The focus is to reduce user resistance, preserve familiar work patterns, and improve consultation management and map-based selection flows for real business users.

## Summary

- Target: legacy admin consultation and map-based workflow screens
- Main users: field operators and public-sector staff, especially users in their 40s to 60s
- Goal: reduce friction, improve task efficiency, and lower input errors
- Core methods: conversational consultation UI, OpenLayers map selection, report automation

## Who This Is For

- Developers modernizing old Java/JSP admin systems
- Teams looking for UI/UX examples that respect existing user habits
- Portfolio builders who want to explain a practical before/after redesign

## Features

- Conversational consultation management UI
- Map-based parcel selection to reduce manual input errors
- Automated operation report flow
- Performance notes for PL/SQL and query optimization examples

## Quick Start

1. Install Java 11+ and Maven.
2. Build from the project root.

```bash
mvn clean package
```

3. Check sample DB/config files under `resources/` when available.

## Before & After

The gallery below shows how the interface changes from dense legacy screens to more guided task flows.

<table>
  <tr>
    <th align="center">Before</th>
    <th align="center">After</th>
  </tr>
  <tr>
    <td align="center" valign="top">
      <img src="images/before_1.png" alt="Before 1" width="320"/><br/>
      <small>Dense text and table-based screen</small>
      <br/><br/>
      <img src="images/before_2.png" alt="Before 2" width="320"/><br/>
      <small>Nested forms and long lists</small>
      <br/><br/>
      <img src="images/before_3.png" alt="Before 3" width="320"/><br/>
      <small>Map flow not integrated</small>
    </td>
    <td align="center" valign="top">
      <img src="images/after_1.png" alt="After 1" width="320"/><br/>
      <small>Conversational consultation with key summary</small>
      <br/><br/>
      <img src="images/after_2.png" alt="After 2" width="320"/><br/>
      <small>Simplified filters and shortcut actions</small>
      <br/><br/>
      <img src="images/after_3.png" alt="After 3" width="320"/><br/>
      <small>Direct map-based selection</small>
    </td>
  </tr>
</table>

## Design Principles

- Keep base text around 16px to 18px for readability.
- Use 44px to 48px minimum control heights to reduce click mistakes.
- Use explicit labels and step-by-step progressive disclosure.
- Preserve enough contrast between text and backgrounds.
- Explain both the cause and next action in error messages.

## Suggested Design Tokens

```css
:root {
  --font-size-base: 18px;
  --line-height: 1.4;
  --button-min-height: 48px;
  --space-section: 24px;
  --accent-color: #2b7cff;
}
```

## Contributing

- Report bugs or setup problems in [Issues](https://github.com/yungi0816/gov-admin-uiux-example/issues).
- Share public-sector or legacy UI improvement ideas in [Discussions](https://github.com/yungi0816/gov-admin-uiux-example/discussions).
- Documentation, accessibility, and screenshot explanation improvements are welcome as pull requests.

## License

No explicit license is currently provided. If this project is intended for open reuse, adding MIT or Apache-2.0 is recommended.
