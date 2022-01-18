FROM python:3.8.10

WORKDIR /app

COPY requirements.txt .

RUN pip3 install -r requirements.txt

COPY ./app .

EXPOSE 5000

ENV FLASK_APP=tanaw
ENV FLASK_ENV=development

RUN python3 -m flask init-db
CMD [ "python3", "-m" , "flask", "run", "--host=0.0.0.0"]