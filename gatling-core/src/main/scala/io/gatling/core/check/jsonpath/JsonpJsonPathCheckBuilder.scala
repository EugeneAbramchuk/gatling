/*
 * Copyright 2011-2020 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.gatling.core.check.jsonpath

import io.gatling.core.session._

trait JsonpJsonPathCheckType

// we have to duplicate JsonPathCheckBuilder because traits can't take parameters (for now)
// so we can't make CheckType a parameter
trait JsonpJsonPathOfType { self: JsonpJsonPathCheckBuilder[String] =>

  def ofType[X: JsonFilter]: JsonpJsonPathCheckBuilder[X] = new JsonpJsonPathCheckBuilder[X](path, jsonPaths)
}

object JsonpJsonPathCheckBuilder {

  def jsonpJsonPath(path: Expression[String], jsonPaths: JsonPaths): JsonpJsonPathCheckBuilder[String] with JsonpJsonPathOfType =
    new JsonpJsonPathCheckBuilder[String](path, jsonPaths) with JsonpJsonPathOfType
}

class JsonpJsonPathCheckBuilder[X: JsonFilter](
    path: Expression[String],
    jsonPaths: JsonPaths
) extends JsonPathCheckBuilderBase[JsonpJsonPathCheckType, X]("jsonpJsonPath", path, jsonPaths)
