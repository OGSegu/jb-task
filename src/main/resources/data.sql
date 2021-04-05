CREATE TABLE templates (
    template_id VARCHAR PRIMARY KEY,
    template VARCHAR
);

CREATE TABLE recipients (
    template_id VARCHAR,
    recipient VARCHAR,
    PRIMARY KEY (template_id, recipient)
);

CREATE TABLE variables (
  template_id VARCHAR,
  recipient VARCHAR,
  PRIMARY KEY (template_id, recipient)
);