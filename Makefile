DEFAULT_GOAL := help

.PHONY: help
help: ## Show this help
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'

run-test: ## run all test, unit | integration | e2e
	mvn clean test

run-build:
	mvn clean install

infra-local-start: ## Run infrastructure locally
	docker-compose -f infrastructure/docker-compose.yml up -d --build

infra-local-stop: ## Stop infrastructure locally
	docker-compose -f infrastructure/docker-compose.yml down